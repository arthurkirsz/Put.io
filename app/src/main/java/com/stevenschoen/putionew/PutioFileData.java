package com.stevenschoen.putionew;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

public class PutioFileData implements Parcelable {
	public boolean isShared;
	public String name;
	public String screenshot;
	public String createdTime;
	public int parentId;
	public boolean hasMp4;
	public String contentType;
	public int contentTypeIndex;
	public String iconUrl;
	public int id;
	public long size;
	
	public boolean isFolder;
	
	public PutioFileData() {
		super();
	}

	public PutioFileData(boolean isShared, String name, String screenshot,
			String createdTime, int parentId, boolean hasMp4,
			String contentType, String iconUrl, int id, long size) {
		super();
		this.isShared = isShared;
		this.name = name;
		this.screenshot = screenshot;
		this.createdTime = createdTime;
		this.parentId = parentId;
		this.hasMp4 = hasMp4;
		this.contentType = contentType;
		for (int i = 0; i < contentTypes.size(); i++) {
			if (contentType.matches(contentTypes.get(i))) {
				this.contentTypeIndex = i;
			}
		}
		this.iconUrl = iconUrl;
		this.id = id;
		this.size = size;

		isFolder = contentType.matches("application/x-directory");
	}

	public static String[] contentTypesArray = new String[] { "file", "application/x-directory",
			"application/x-iso9660-image", "application/zip",
			"application/x-rar", "application/x-dosexec", "application/pdf", "text/plain" };
	public static int[] icons = new int[] { R.drawable.ic_putio_file, R.drawable.ic_putio_folder, R.drawable.ic_putio_image,
			R.drawable.ic_putio_compressed, R.drawable.ic_putio_compressed, R.drawable.ic_putio_file, R.drawable.ic_putio_pdf, R.drawable.ic_putio_text};
	
	List<String> contentTypes = Arrays.asList(contentTypesArray);

	@Override
	public int describeContents() {
		return 0;
	}
	
	public PutioFileData(Parcel in) {
		readFromParcel(in);
	}
	
	private void readFromParcel(Parcel in) {
		// We just need to read back each
		// field in the order that it was
		// written to the parcel
		this.isShared = (Boolean) in.readValue(ClassLoader.getSystemClassLoader());
		this.name = in.readString();
		this.screenshot = in.readString();
		this.createdTime = in.readString();
		this.parentId = in.readInt();
		this.hasMp4 = (Boolean) in.readValue(ClassLoader.getSystemClassLoader());
		this.contentType = in.readString();
		this.contentTypeIndex = in.readInt();
		this.iconUrl = in.readString();
		this.id = in.readInt();
		this.size = in.readLong();
	}

	@Override
	public void writeToParcel(Parcel out, int flags) {
		out.writeValue(this.isShared);
		out.writeString(this.name);
		out.writeString(this.screenshot);
		out.writeString(this.createdTime);
		out.writeInt(this.parentId);
		out.writeValue(this.hasMp4);
		out.writeString(this.contentType);
		out.writeInt(this.contentTypeIndex);
		out.writeString(this.iconUrl);
		out.writeInt(this.id);
		out.writeLong(this.size);
	}
	
	public static final Creator CREATOR = new Creator() {
		public PutioFileData createFromParcel(Parcel in) {
			return new PutioFileData(in);
		}

		public PutioFileData[] newArray(int size) {
			return new PutioFileData[size];
		}
	};
}